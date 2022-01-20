package com.tma.core;

import com.tma.course.Course;
import com.tma.course.CourseRepository;
import com.tma.review.Review;
import com.tma.user.User;
import com.tma.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public DatabaseLoader(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Course course = new Course("Java Basics", "https://www.teamtreehouse.com/library/java-basics");
        course.addReview(new Review(3, "You ARE a dork!!!"));
        courseRepository.save(course);

        String[] templates = {
                "Up and Running with %s",
                "%s Basics",
                "%s for Beginners",
                "%s for Neckbeards",
                "Under the hood: %s"
        };

        String[] buzzwords = {
                "Spring REST Data",
                "Java 8",
                "Scala",
                "Groovy",
                "Hibernate",
                "Spring HATEOAS"
        };

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        List<User> students = Arrays.asList(
                new User("jacobproffer", "Jacob",  "Proffer", "password", roles),
                new User("mlnorman", "Mike",  "Norman", "password", roles),
                new User("k_freemansmith", "Karen",  "Freeman-Smith", "password", roles),
                new User("seth_lk", "Seth",  "Kroger", "password", roles),
                new User("mrstreetgrid", "Java",  "Vince", "password", roles),
                new User("anthonymikhail", "Tony",  "Mikhail", "password", roles),
                new User("boog690", "AJ",  "Teacher", "password", roles),
                new User("faelor", "Erik",  "Faelor Shafer", "password", roles),
                new User("christophernowack", "Christopher",  "Nowack", "password", roles),
                new User("calebkleveter", "Caleb",  "Kleveter", "password", roles),
                new User("richdonellan", "Rich",  "Donnellan", "password", roles),
                new User("albertqerimi", "Albert",  "Qerimi", "password", roles)
        );
        userRepository.saveAll(students);
        roles.add("ROLE_ADMIN");
        userRepository.save(new User("thangnguyen", "Thang", "Nguyen","ok", roles));
        List<Course> bunchOfCourses = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzword = buzzwords[i % buzzwords.length];
                    String title = String.format(template, buzzword);
                    Course c = new Course(title, "http://www.example.com");
                    Review review = new Review((i % 5) + 1, String.format("Moar %s please!!!", buzzword));
                    review.setReviewer(students.get(i % students.size()));
                    c.addReview(review);
                    bunchOfCourses.add(c);
                });
        courseRepository.saveAll(bunchOfCourses);
    }
}
