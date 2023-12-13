import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.util.List;

class PayrollDBServiceTest {

    private PayrollDBService payrollDBService;

    @BeforeEach
    void setUp() {
        payrollDBService = new PayrollDBService();
    }

    @Test
    void testGetActiveEmployeePayrollData() {
        assertDoesNotThrow(() -> {
            List<EmployeePayroll> employees = payrollDBService.getActiveEmployeePayrollData();
            assertNotNull(employees);
        });
    }

    @Test
    void testUpdateEmployeeSalary() {
        assertDoesNotThrow(() -> {
            boolean result = payrollDBService.updateEmployeeSalary("Amit", 75000.00);
            assertTrue(result);
        });
    }

    @Test
    void testGetEmployeesJoinedInRange() {
        assertDoesNotThrow(() -> {
            Date startDate = Date.valueOf("2023-01-01");
            Date endDate = Date.valueOf("2023-12-31");
            List<EmployeePayroll> employees = payrollDBService.getEmployeesJoinedInRange(startDate, endDate);
            assertNotNull(employees);
        });
    }

    @Test
    void testGetSalaryStatsByGender() {
        assertDoesNotThrow(() -> {
            var stats = payrollDBService.getSalaryStatsByGender();
            assertNotNull(stats);
        });
    }

    @Test
    void testAddAndRemoveEmployee() {
        assertDoesNotThrow(() -> {
            EmployeePayroll newEmployee = payrollDBService.addEmployeeToPayroll(1000, "Test", 0, new Date(2023-12-12), "M", "0", "0", "0", 0, 0, 0, 0, 0);
            assertNotNull(newEmployee);

            boolean isRemoved = payrollDBService.removeEmployee(newEmployee.getId());
            assertTrue(isRemoved);
        });
    }

    @AfterEach
    void tearDown() {
    }

}