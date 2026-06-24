package com.gabriel.spring_boot_rest.controller;

import com.gabriel.spring_boot_rest.model.JobPost;
import com.gabriel.spring_boot_rest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobRestController {

    @Autowired
    private JobService service;

    @GetMapping("/JobPosts")
    public List<JobPost> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping("/JobPost/{postId}")
    public JobPost getJob(@PathVariable int postId) {
        return service.getJob(postId);
    }

    /*
    No 'design' de APIs REST, usamos variáveis de caminho ({'id'}) para identificar um recurso único (ex: /jobPost/5).
    Para buscas e filtros, o padrão global é usar parâmetros de consulta (Query Params), representados pelo sinal de interrogação na URL (ex: /jobPosts/search?keyword=java).
    O motivo? Palavras-chave podem conter espaços ou caracteres especiais (ex: "C++ 'developer'"), e isso quebra URLs baseadas em Path.*/
//    @GetMapping("JobPost/keyword/{keyword}")
//    public List<JobPost> search(@PathVariable String keyword) {
//        return service.search(keyword);
//    }

    @GetMapping("JobPost/search")
    public List<JobPost> search(@RequestParam String keyword) {
        return service.search(keyword);
    }

    @PostMapping("/JobPost") //O ResponseEntity para retornar 'Feedback' Visual (201 Created) e confirmação dos dados
    public ResponseEntity<JobPost> addJob(@RequestBody JobPost jobPost) {
        JobPost savedJob = service.addJob(jobPost);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedJob);
    }

    @PutMapping("JobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost) {
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

//    @PutMapping("/JobPost/{postId}")
//    public ResponseEntity<JobPost> changeJob(@PathVariable("postId") int postId, @RequestBody JobPost jobPost) {
//        service.changeJob(jobPost, postId);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(jobPost);
//
//    }

    @DeleteMapping("/JobPost/{postId}")
    public void deleteJob(@PathVariable int postId) {
        service.deleteJob(postId);
    }

    @GetMapping("/load")
    public String loadData() {
        service.load();
        return "success";
    }

}