import java.util.*;

public class OnboardingService {
    private final StudentRepository repo;
    private final InputParser parser;
    private final StudentValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentRepository repo, InputParser parser,
            StudentValidator validator, OnboardingPrinter printer) {
        this.repo = repo;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        // 1. Parse
        Map<String, String> kv = parser.parse(raw);
        String name = parser.get(kv, "name");
        String email = parser.get(kv, "email");
        String phone = parser.get(kv, "phone");
        String program = parser.get(kv, "program");

        // 2. Validate
        List<String> errors = validator.validate(name, email, phone, program);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        // 3. Generate ID & create record
        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        // 4. Persist
        repo.save(rec);

        // 5. Print confirmation
        printer.printSuccess(id, repo.count(), rec);
    }
}
