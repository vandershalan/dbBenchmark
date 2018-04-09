package com.lppsa.tests.dbBenchmark;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAsync
@Slf4j
public class DbBenchmarkApplication implements CommandLineRunner {

	@Autowired
	BenchmarkService benchmarkService;

	public static void main(String[] args) {
		SpringApplication.run(DbBenchmarkApplication.class, args).close();
	}

	@Override
	public void run(String... args) throws Exception {
		
		int count = 1000000;
		long startTime = System.currentTimeMillis();

		benchmarkService.BenchmarkInserts(count);

		ThreadPoolTaskExecutor exec = (ThreadPoolTaskExecutor) asyncExecutor();
		do {
			log.info("Queue size: " + exec.getThreadPoolExecutor().getQueue().size());
			Thread.sleep(1000);
		} while (exec.getThreadPoolExecutor().getQueue().size() > 1);
		
		long stopTime = System.currentTimeMillis();
		long time = stopTime - startTime;

		log.info("Test took: " + (time) + " ms");
		log.info("Inserts per second: " + (double)count / time * 1000);
		
	}
	
	@Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(64);
        executor.setMaxPoolSize(64);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setQueueCapacity(2000000);
        executor.setThreadNamePrefix("dbBench-");
        executor.initialize();
        return executor;
    }

}
