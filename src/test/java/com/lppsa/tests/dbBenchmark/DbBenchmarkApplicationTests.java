package com.lppsa.tests.dbBenchmark;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DbBenchmarkApplicationTests {

	@Autowired
	RfidDao dao;

	public void selectTest() {

		log.info("RFID Events count: " + dao.getEventsCount());

		List<RfidEvent> events = dao.getAllEvents();
		log.info("RFID Events: " + events);
	}

	@Test
	public void insert100Test() {

		int count = 100;
		int sn = dao.getMaxSN();
		log.info("RFID Events before test: " + dao.getEventsCount());
		log.info("RFID Max SN before test: " + sn);

		long startTime = System.currentTimeMillis();

		for (int i = 0; i < count; i++) {
			RfidEvent event = new RfidEvent("123456789ABCDEF" + ++sn, "EAN" + sn, sn);
			dao.addEventByPreparedStatement(event);
		}

		long stopTime = System.currentTimeMillis();

		log.info("RFID Events after test: " + dao.getEventsCount());
		log.info("RFID Max SN after test: " + sn);

		long time = stopTime - startTime;
		log.info("Test took: " + (time) + " ms");
		log.info("Inserts per second: " + (double)count / time * 1000);

	}
}
