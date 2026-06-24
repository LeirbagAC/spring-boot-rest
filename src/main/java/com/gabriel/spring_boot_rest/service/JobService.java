package com.gabriel.spring_boot_rest.service;

import com.gabriel.spring_boot_rest.model.JobPost;
import com.gabriel.spring_boot_rest.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepo repo;

    public JobPost addJob(JobPost jobPost) {
        return repo.save(jobPost);
    }

    public List<JobPost> getAllJobs() {
        return repo.findAll();
    }

    public JobPost getJob(int postId) {
        return repo.findById(postId).orElse(new JobPost());
    }

    public void updateJob(JobPost jobPost) {
        repo.save(jobPost);
    }

    public void deleteJob(int postId) {
        repo.deleteById(postId);
    }

    public void load() {
        List<JobPost> jobs = new ArrayList<>(Arrays.asList(
                new JobPost(null, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
                        List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),
                new JobPost(null, "Frontend Developer", "Experience in building responsive web applications using React", 3,
                        List.of("HTML", "CSS", "JavaScript", "React")),
                new JobPost(null, "Data Scientist", "Strong background in machine learning and data analysis", 4,
                        List.of("Python", "Machine Learning", "Data Analysis")),
                new JobPost(null, "Network Engineer", "Design and implement computer networks for efficient data communication", 5,
                        List.of("Networking", "Cisco", "Routing", "Switching")),
                new JobPost(null, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
                        List.of("iOS Development", "Android Development", "Mobile App")),
                new JobPost(null, "testes C++", "Qualquer C++ coisa", 4,
                        List.of("C++")),
                new JobPost(null, "Backend Developer",
                        "Experience building scalable REST APIs and microservices", 3,
                        List.of("Java", "Spring Boot", "PostgreSQL", "Docker")),

                new JobPost(null, "Full Stack Developer",
                        "Develop and maintain frontend and backend applications", 4,
                        List.of("React", "Node.js", "TypeScript", "MongoDB")),

                new JobPost(null, "DevOps Engineer",
                        "Automate deployment pipelines and manage cloud infrastructure", 5,
                        List.of("AWS", "Docker", "Kubernetes", "Jenkins")),

                new JobPost(null, "Python Developer",
                        "Develop backend applications and automation tools", 2,
                        List.of("Python", "Django", "Flask", "PostgreSQL")),

                new JobPost(null, "QA Engineer",
                        "Design and execute automated software tests", 2,
                        List.of("JUnit", "Selenium", "TestNG", "Automation")),

                new JobPost(null, "Cloud Engineer",
                        "Manage cloud infrastructure and deployment pipelines", 4,
                        List.of("AWS", "Terraform", "Linux", "Docker")),

                new JobPost(null, "Cybersecurity Analyst",
                        "Monitor systems and perform security assessments", 3,
                        List.of("Network Security", "Kali Linux", "SIEM", "Firewalls")),

                new JobPost(null, "Machine Learning Engineer",
                        "Build and deploy machine learning models", 4,
                        List.of("Python", "TensorFlow", "PyTorch", "Scikit-Learn")),

                new JobPost(null, "Database Administrator",
                        "Maintain and optimize enterprise databases", 5,
                        List.of("MySQL", "PostgreSQL", "Backup", "Performance Tuning")),

                new JobPost(null, "React Developer",
                        "Create modern and responsive web applications", 2,
                        List.of("React", "JavaScript", "CSS", "Redux")),

                new JobPost(null, "Angular Developer",
                        "Develop enterprise web applications", 3,
                        List.of("Angular", "TypeScript", "RxJS", "HTML")),

                new JobPost(null, "Node.js Developer",
                        "Build RESTful APIs and backend services", 3,
                        List.of("Node.js", "Express", "MongoDB", "JWT")),

                new JobPost(null, "Software Architect",
                        "Design scalable software solutions and architectures", 7,
                        List.of("Microservices", "Java", "Cloud", "System Design")),

                new JobPost(null, "Business Intelligence Analyst",
                        "Develop reports and dashboards for business insights", 3,
                        List.of("Power BI", "SQL", "Excel", "ETL")),

                new JobPost(null, "Android Developer",
                        "Develop native Android mobile applications", 3,
                        List.of("Kotlin", "Android SDK", "Jetpack", "Room")),

                new JobPost(null, "iOS Developer",
                        "Build and maintain iOS applications", 3,
                        List.of("Swift", "UIKit", "Xcode", "Core Data")),

                new JobPost(null, "PHP Developer",
                        "Develop web applications and APIs using PHP", 2,
                        List.of("PHP", "Laravel", "MySQL", "REST")),

                new JobPost(null, "C# Developer",
                        "Create enterprise solutions using .NET technologies", 4,
                        List.of("C#", ".NET", "SQL Server", "ASP.NET Core")),

                new JobPost(null, "Game Developer",
                        "Develop games for desktop and mobile platforms", 3,
                        List.of("Unity", "C#", "Game Design", "Physics")),

                new JobPost(null, "AI Engineer",
                        "Implement artificial intelligence solutions for businesses", 5,
                        List.of("Python", "LLMs", "NLP", "Deep Learning")),

                new JobPost(null, "Data Engineer",
                        "Build and maintain data pipelines for analytics", 4,
                        List.of("Python", "Apache Spark", "Kafka", "SQL"))
        ));

        repo.saveAll(jobs);
    }

    public List<JobPost> search(String keyword) {
        return repo.findByPostProfileContainingIgnoreCaseOrPostDescContainingIgnoreCase(keyword, keyword);
    }
}
