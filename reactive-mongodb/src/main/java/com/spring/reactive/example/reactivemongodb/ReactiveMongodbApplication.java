package com.spring.reactive.example.reactivemongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ReactiveMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongodbApplication.class, args);

		/************* FlatMap *******************/
		String[][] dataArray = new String[][] { { "a", "b" }, { "c", "d" }, { "e", "f" }, { "g", "h" } };

		List<String> listOfAllChars = Arrays.stream(dataArray).flatMap(x -> Arrays.stream(x))
				.collect(Collectors.toList());

		System.out.println(listOfAllChars);

		/*********** combining flux using concat***************/
		Flux<Integer> evenNumbers = Flux.range(2, 14).filter(x -> x % 2 == 0); // i.e. 2, 4

		Flux<Integer> oddNumbers = Flux.range(1, 5).filter(x -> x % 2 > 0); // ie. 1, 3, 5

		Flux<Integer> oddNumbers1 = Flux.range(5, 14).filter(x -> x % 2 > 0); // 5, 7, 11 
		
		Flux<Integer> fluxOfIntegers = Flux.concat(evenNumbers, oddNumbers, oddNumbers1);
		
		fluxOfIntegers.subscribe(System.out::println);
		
		/*********** combining mono using zip ***************/
		Mono<String> firstMono = Mono.just("one");
		  Mono<String> secondMono = Mono.just("two");
		  Mono<Integer> thirdMono = Mono.just(3);
		  
		ArrayList<Mono<?>> publishers = new ArrayList<>();
		publishers.add(firstMono);
		publishers.add(secondMono);
		publishers.add(thirdMono);
		  
		  Mono.zip(
				  publishers,
				  arr -> {
					  System.out.println("Zip monos");
					  System.out.println(arr[0]);
					  System.out.println(arr[1]);
					  System.out.println(arr[2] +"\n");
					  return Mono.empty();
				  }
				  )
	      .subscribe();
		  
		  System.out.println("Monos Concat");
		  firstMono.concatWith(secondMono).subscribe(System.out::println);
	}

}
