package escudeler.example.apilisten.service;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import escudeler.example.apilisten.config.ApplicationConfig;
import escudeler.example.apilisten.exception.InternalServerErrorException;
import escudeler.example.apilisten.request.HelloRequest;
import escudeler.example.apilisten.response.SpeakHelloResponse;
import escudeler.example.apispeak.grpc.HelloResponse;
import escudeler.example.apispeak.grpc.PersonRequest;
import escudeler.example.apispeak.grpc.SpeakGrpcServiceGrpc.SpeakGrpcServiceBlockingStub;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Log4j2
@RequiredArgsConstructor
@Service
public class ListenService {

	@GrpcClient("grpc-server")
	private SpeakGrpcServiceBlockingStub speakBlockingStub;

	private final RestTemplate restTemplate;
	private final ApplicationConfig applicationConfig;

	public String sayHelloGrpc(HelloRequest helloRequest) {
		try {
			PersonRequest personRequest = PersonRequest.newBuilder()
					.setPersonName(helloRequest.getPersonName())
					.build();
			log.info("Calling gRPC to say Hello to {}", personRequest.getPersonName());
			HelloResponse helloResponse = speakBlockingStub.hello(personRequest);

			return helloResponse.getReply();

		} catch (Exception e) {
			log.error("Error to sending hello.", e);
			throw new InternalServerErrorException();
		}
	}

	public String sayhelloHttp(HelloRequest helloRequest) {
		try {
			log.info("Call http to validate people {}...", helloRequest.getPersonName());
			URI uri = new UriTemplate(applicationConfig.getHttpSpeakAddress()).expand();

			RequestEntity<HelloRequest> requestEntity = RequestEntity.post(uri)
					.contentType(MediaType.APPLICATION_JSON)
					.body(helloRequest);
			SpeakHelloResponse helloResponse = restTemplate.exchange(requestEntity, SpeakHelloResponse.class).getBody();
			return helloResponse != null ? helloResponse.getReply() : "Invalid Request.";

		} catch (RestClientException e) {
			log.error("MS Calling Error -> http Speak : ", e);
			// TODO make a better implementation for REST
			throw new InternalServerErrorException();
		}
	}
}
