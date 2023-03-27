package com.example.silvertiger.controller;

import com.example.silvertiger.dto.BoardDto;
import com.example.silvertiger.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequiredArgsConstructor
//경로가 반복될 경우 대표 주소를 작성해서 하위 매서드에서 반복적으로 작성하지 않아도 된다
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    //Spring에서 같은 매서드와 주소를 두개 이상 사용할 수 없다.

    @PostMapping("/save")
    /*BoardDto와 save.html안에 있는 네임과 필드 값이 동일하다면 해당하는 세터 메서드로 담아줘서 하나의 DTO 객체로 받을 수 있다.*/
    public String save(@ModelAttribute BoardDto boardDto){
        System.out.println("boardDTO = " + boardDto);
        boardService.save(boardDto);
        return "index";
    }

    //데이터를 가져올 때는 모델 객체를 사용
    @GetMapping("/")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        // 전체 게시글 목록이기 때문에 여러개이기 때문에 List를 사용
        List<BoardDto> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    //경로상에서 값을 받아올 때에는 @PathVariable을 사용
    public String findById(@PathVariable Long id, Model model){
        /*
          해당 게시글의 조회수를 하나 올리고
          게시글 데이터를 가져와서 detail.html에 출력
        */
        //두 개 매서드 호출, boardDto 객체에 findById 매서드를 호출하여 model 파라미터에 담아 디테일 html로 넘어간다.
        boardService.updateHits(id);
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDto);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDto boardDto, Model model) {
        BoardDto board = boardService.update(boardDto);
        model.addAttribute("board", board);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }
}
