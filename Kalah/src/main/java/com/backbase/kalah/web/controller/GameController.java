package com.backbase.kalah.web.controller;

import com.backbase.kalah.constant.Status;
import com.backbase.kalah.exception.InvalidMoveException;
import com.backbase.kalah.game.Game;
import com.backbase.kalah.web.vo.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by f553457 on 11/7/16.
 */
@Controller
@RequestMapping("/game")
public class GameController {

    private Game game;
    @Autowired( required = true )
    public GameController(Game game){
        this.game = game;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        GameViewModel viewModel = new GameViewModel(game);
        return new ModelAndView("board", "game", viewModel);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String move(@RequestParam(name = "pitId")  long pits) throws InvalidMoveException {
        game.sow(pits);
        return "redirect:/game";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String restart(){
        game.reset();
        return "redirect:/game";
    }


}