package com.gabriel.spring_boot_rest.repo;

import com.gabriel.spring_boot_rest.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {

    List<JobPost> findByPostProfileContainingIgnoreCaseOrPostDescContainingIgnoreCase(String profile, String desc);

}

//public List<JobPost> getAllJobs() {
//    return jobs;
//}
//
//public void addJob(JobPost job){
//    jobs.add(job);
////        System.out.println(jobs);
//}
//
//public JobPost getJob(int postId) {
//    for(JobPost job : jobs) {
//        if(job.getPostId() == postId) {
//            return job;
//        }
//    }
//
//    return null;
//}
//
//public void changeJob(JobPost jobPost ,int postId) {
//    for(JobPost job : jobs) {
//        if(job.getPostId() == postId) {
//            job.setPostProfile(jobPost.getPostProfile());
//            job.setPostDesc(jobPost.getPostDesc());
//            job.setReqExperience(jobPost.getReqExperience());
//            job.setPostTechStack(jobPost.getPostTechStack());
//
////                System.out.println(jobs);
//        }
//    }
//}
//
//public void deleteJob(int postId) {
//    jobs.removeIf(job -> job.getPostId() == postId);
//}