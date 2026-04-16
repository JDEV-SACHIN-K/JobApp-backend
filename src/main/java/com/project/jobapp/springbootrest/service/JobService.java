package com.project.jobapp.springbootrest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jobapp.springbootrest.model.JobPost;
import com.project.jobapp.springbootrest.repo.JobRepo;

@Service
public class JobService {

	@Autowired
	public JobRepo repo;

	public List<JobPost> getAllJobs() {
		return repo.findAll();
	}

	public void addJob(JobPost jobPost) {
		repo.save(jobPost);
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
		if (repo.count() > 0) {
			return;
		}

		List<JobPost> jobs =
				new ArrayList<>(List.of(
					new JobPost(1, "Senior Java Developer", "Build scalable backend systems with Spring Boot and microservices architecture. Work with REST APIs and cloud deployment.", 5, List.of("Java", "Spring Boot", "Docker", "AWS", "Microservices")),
					new JobPost(2, "Full Stack Developer", "Develop modern web applications using React and Node.js. Take ownership of features from frontend to backend.", 3, List.of("JavaScript", "React", "Node.js", "PostgreSQL", "MongoDB")),
					new JobPost(3, "DevOps Engineer", "Manage CI/CD pipelines, infrastructure as code, and cloud deployments. Optimize system performance and reliability.", 4, List.of("Docker", "Kubernetes", "Jenkins", "Terraform", "AWS")),
					new JobPost(4, "Data Scientist", "Build machine learning models and data pipelines. Analyze complex datasets and derive actionable insights.", 5, List.of("Python", "Machine Learning", "TensorFlow", "SQL", "Pandas")),
					new JobPost(5, "Frontend Developer", "Create responsive and interactive UIs with React and TypeScript. Collaborate with designers and backend teams.", 2, List.of("React", "TypeScript", "CSS", "Webpack", "Jest")),
					new JobPost(6, "Cloud Architect", "Design and implement cloud infrastructure solutions. Lead technical decisions for enterprise systems.", 6, List.of("AWS", "Azure", "Infrastructure", "Security", "High Availability")),
					new JobPost(7, "Backend Engineer", "Develop robust REST APIs and databases. Implement authentication, caching, and optimization strategies.", 3, List.of("Java", "Spring", "PostgreSQL", "Redis", "REST")),
					new JobPost(8, "QA Automation Engineer", "Write automated tests and build testing frameworks. Ensure code quality and reliability across features.", 2, List.of("Selenium", "Java", "TestNG", "CI/CD", "Automation")),
					new JobPost(9, "Mobile Developer", "Build native Android and iOS applications. Optimize mobile performance and user experience.", 4, List.of("Android", "iOS", "React Native", "Kotlin", "Swift")),
					new JobPost(10, "Security Engineer", "Identify vulnerabilities and implement security solutions. Manage access control and compliance standards.", 5, List.of("Cybersecurity", "Encryption", "Penetration Testing", "OWASP", "Network Security")),
					new JobPost(11, "Database Administrator", "Manage and optimize database systems. Ensure data integrity, backup, and disaster recovery.", 4, List.of("PostgreSQL", "Oracle", "MySQL", "Performance Tuning", "Backup")),
					new JobPost(12, "AI/ML Engineer", "Develop artificial intelligence models and algorithms. Implement deep learning solutions for real-world problems.", 5, List.of("Python", "PyTorch", "TensorFlow", "NLP", "Computer Vision")),
					new JobPost(13, "Solutions Architect", "Design enterprise solutions and technical implementations. Guide clients through system architecture decisions.", 6, List.of("Architecture", "Cloud Design", "Best Practices", "Enterprise Systems", "Leadership")),
					new JobPost(14, "GraphQL Developer", "Build efficient APIs using GraphQL. Optimize data fetching and improve client-server communication.", 3, List.of("GraphQL", "Node.js", "Apollo", "REST", "API Design")),
					new JobPost(15, "Embedded Systems Engineer", "Develop firmware and embedded software. Work with IoT devices and real-time systems.", 4, List.of("C", "C++", "Embedded Linux", "IoT", "RTOS"))
				));

		repo.saveAll(jobs);
	}

	public List<JobPost> search(String keyword) {
		return repo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
	}
}
