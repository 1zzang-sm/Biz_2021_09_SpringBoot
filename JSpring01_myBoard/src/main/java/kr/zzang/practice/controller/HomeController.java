package kr.zzang.practice.controller;

import kr.zzang.practice.domain.BoardRepository;
import kr.zzang.practice.models.BoardDTO;
import kr.zzang.practice.service.BoardService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    private BoardService boardService;

    public HomeController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String list(Model model) {
        List<BoardDTO> boardDTOList = boardService.getBaordList();
        model.addAttribute("postList", boardDTOList);
        return "board/list.html";
    }

    @RequestMapping(value="/post", method=RequestMethod.GET)
    public String post(){
        return "board/post.html";
    }

    @RequestMapping(value="/post", method=RequestMethod.POST)
    public String write(BoardDTO boardDTO){
        boardService.savePost(boardDTO);
        return "redirect:/";
    }

    @RequestMapping(value="/post/{id}", method=RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.getPost(id);
        model.addAttribute("post", boardDTO);
        return "board/detail.html";
    }

    @RequestMapping(value="/post/edit/{id}", method=RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model){
        BoardDTO boardDTO = boardService.getPost(id);
        model.addAttribute("post", boardDTO);
        return "board/edit.html";
    }

    @RequestMapping(value="/post/edit/{id}", method=RequestMethod.PUT)
    public String update(BoardDTO boardDTO){
        boardService.savePost(boardDTO);
        return "redirect:/";
    }

    @RequestMapping(value="/post/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "redirect:/";
    }
}
