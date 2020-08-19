package in.nareshit.raghu.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import in.nareshit.raghu.model.Employee;
import in.nareshit.raghu.reader.EmployeeReaderService;
import in.nareshit.raghu.repo.EmployeeRepository;

@Component
public class EmployeeSchedulerService {
	@Autowired
	private EmployeeReaderService service;
	@Autowired
	private EmployeeRepository repo;

	//every day night / 10PM 
	@Scheduled(cron = "${app.schedule}")
	public void executeEmployeeExcelImport() {
		List<Employee> emps = service.readData();
		for(Employee e:emps) {
			if(!repo.existsById(e.getEmpId()))
				repo.save(e);
		}
	}
}
