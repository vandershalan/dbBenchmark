package homorg.tests.dbBenchmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BenchmarkService {
	
	@Autowired
	RfidDao dao;
	
	public void BenchmarkInserts(int count) {
		
		int sn = dao.getMaxSN();
		log.info("RFID Events before test: " + dao.getEventsCount());
		log.info("RFID Max SN before test: " + sn);

		for (int i = 0; i < count; i++) {
			RfidEvent event = new RfidEvent(++sn, "123456789ABCDEF" + sn, "EAN" + sn, sn);
			dao.addEventByPreparedStatement(event);
			//dao.addEvent(event);
		}

		log.info("RFID Events after test: " + dao.getEventsCount());
		log.info("RFID Max SN after test: " + sn);

	}

}
