//working solution
package com.example;

import com.example.model.Course;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup
                .connect("https://skillsbuild.org/college-students/digital-credentials")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();
        Elements categories = doc.getElementsByClass("pb-12");
        List<Course> courses = new ArrayList<>();
        for (Element c : categories) {
            Element category = c.getElementsByClass("badge-section-title bx--productive-heading-06 pt-8 pb-12 max-w-9/10").getFirst();
            Elements categoryElements = c.getElementsByClass("mb-16 bx--row");
            for (Element cE : categoryElements) {

                Course course = new Course();
                course.setCategory(category.text());
                Element title = cE.selectFirst(".bx--expressive-heading-03.mb-4");
                Element language = cE.select(".ml-1").get(0);
                Element duration = cE.select(".ml-1").get(2);
                Element description = cE.getElementsByClass("bx--body-long-02 max-w-9/10").select("p").getFirst();
                String link = cE.getElementsByClass("flex flex-col md:flex-row").select("a").getLast().attr("href");

                course.setTitle(title.text());
                course.setDuration(duration.text());
                course.setLanguage(language.text());
                course.setDescription(description.text());
                if (link.startsWith("/")){
                    link= "https://skillsbuild.org"+ link;
                    course.setLink(link);
                    System.out.println(link);
                }
                courses.add(course);

                // Regular expression to find the numbers in the duration string.
                Pattern pattern = Pattern.compile("\\d+");
                //matching the pattern above to the duration for the course in this iteration.
                Matcher matcher = pattern.matcher(duration.text());
                // calculating the points by multiplying the duration by 2.
                String points = "";
                while (matcher.find()) {
                    points += matcher.group();
                }
                System.out.println(course);
            }
        }


    }

}
