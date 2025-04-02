import freemarker.template.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {
    private RepositoryImagine repository;

    public ReportCommand(RepositoryImagine repository) {
        this.repository = repository;
    }

    @Override
    public void execute() throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File("templates"));
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("report.ftl");

        Map<String, Object> data = new HashMap<>();
        data.put("imagini", repository.getImagini());

        Writer fileWriter = new FileWriter(new File("report.html"));
        template.process(data, fileWriter);
        fileWriter.close();

        System.out.println("Raportul a fost generat.");
    }
}
