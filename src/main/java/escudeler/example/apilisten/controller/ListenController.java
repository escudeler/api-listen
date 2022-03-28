package escudeler.example.apilisten.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import escudeler.example.apilisten.request.HelloRequest;
import escudeler.example.apilisten.service.ListenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@Log4j2
public class ListenController {

	private final ListenService listenService;

	@PostMapping("/hello-http")
	public ResponseEntity<String> helloHttp(@RequestBody HelloRequest helloRequest) {
		String reply = listenService.sayhelloHttp(helloRequest);

		return ResponseEntity.ok().body(reply);
	}

	@PostMapping("/hello-grpc")
	public ResponseEntity<String> helloGrpc(@RequestBody HelloRequest helloRequest) {
		String reply = listenService.sayHelloGrpc(helloRequest);

		return ResponseEntity.ok().body(reply);
	}

	@PostMapping("/hello-thousand-grpc")
	public ResponseEntity<String> helloThousandGrpc(@RequestBody HelloRequest helloRequest) {
		long ini = System.currentTimeMillis();
		String personName = helloRequest.getPersonName();
		for (int i = 0; i <= 999; i++) {
			helloRequest.setPersonName(personName + i);
			listenService.sayHelloGrpc(helloRequest);
		}
		long fim = System.currentTimeMillis();
		String reply = (fim - ini) + " ms";
		log.info(reply);
		return ResponseEntity.ok().body(reply);
	}

	@PostMapping("/hello-thousand-http")
	public ResponseEntity<String> helloThousandHttp(@RequestBody HelloRequest helloRequest) {
		long ini = System.currentTimeMillis();
		String personName = helloRequest.getPersonName();
		for (int i = 0; i <= 999; i++) {
			helloRequest.setPersonName(personName + i);
			listenService.sayhelloHttp(helloRequest);
		}
		long fim = System.currentTimeMillis();
		String reply = (fim - ini) + " ms";
		return ResponseEntity.ok().body(reply);
	}
}
