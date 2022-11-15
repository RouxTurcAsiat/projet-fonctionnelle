package project

/**
 * @file src/Function.scala
 * @brief File containing the functions asked in project statement
 * @author Mae K.
 * @author Mustafa-Can K.
 * @author Jordan D.
 * @see (TODO pointer avec le PDF)
 */

import project.Operators.LogicalOp
import project.Utils.extractVar, project.Utils.Option.map

type Line = List[Boolean]
type Tableau = List[Line]

object Functions:
    def semtab(formula: List[LogicalOp]): Tableau = 
        val vars = extractVar(formula).toList
        List.range(0, math.pow(2, vars.length).toInt)
            .map((x: Int) => String.format(s"%0${vars.length}d", x.toBinaryString.toInt))
            .map(_.zipWithIndex.foldLeft(Map.empty[String, Char])((acc, x) => acc + (vars(x._2) -> x._1)))
            .map((x: Map[String, Char]) => formula.map(_.eval(x)))

    def isSatisfiable(formula: List[LogicalOp]): Boolean =
        val tab = semtab(formula)
        val req = List.range(1, formula.length + 1).map(_ > 0)
        tab.filter((x: Line) => x == req).length >= 1

    /*def isValid(formula: List[LogicalOp]): Boolean =
        val nonFormula = formula.map(if(1) 0 else if(0) 1)
        if(isSatisfiable(nonFormula))
            true
        else
            false*/

    def isValid(formula: List[LogicalOp], f: List[Boolean]): Boolean =
        val tab = models(formula)
        def loop(t: Tableau): Boolean =
            if(t.isEmpty)
                false
            else if(t.head == f)
                true
            else
                loop(t.tail)
        loop(tab) 

    def isTautology(formula: List[LogicalOp]): Boolean =
        val tab = semtab(formula)
        val req = List.range(1, formula.length + 1).map(_ > 0)
        tab.filter((x: Line) => x == req).length == tab.length

    def isContradiction(formula: List[LogicalOp]): Boolean =
        val tab = semtab(formula)
        val req = List.range(1, formula.length + 1).map(_ < 0)
        tab.filter((x: Line) => x == req).length == tab.length

    /*def models(formula: List[LogicalOp]): Tableau =
        /*val tab = semtab(formula)
        val req = List.range(1, formula.length + 1).map(_ < 0)
        val s = tab.filter((x: Line) => x == req).length*/
        val vars = extractVar(formula).toList
        /*if(isSatisfiable(formula))
            List.range(0, math.pow(2, vars.length).toInt)
            .map((x: Line) => x.isSatisfiable)
        else
            Nil*/

        val tab = semtab(formula)
        if(isSatisfiable((formula(1))))
            tab*/

    def models(formula: List[LogicalOp]): Tableau =
        val tab = semtab(formula)
        val req = List.range(1, formula.length + 1).map(_ > 0)
        tab.zipWithIndex.filter((x: Line, y: Int) => x == req).map(_(1).toBinaryString.toList.map(_ == '1'))

    /*def counterexamples(formula: List[LogicalOp]): Tableau = 
        val tab = semtab(formula)
        tab.filter((x: Line) => x != models(formula))*/

    def counterexamples(formula: List[LogicalOp]): Tableau = 
        /*val tab = semtab(formula).zipWithIndex
        val modelsIndex = models(formula).map((x) => Integer.parseInt(x.map(if _ then "1" else "0").mkString, 2))
        tab.filterNot((x: Line, y: Int) => modelsIndex.contains(y)).map(_(1).toBinaryString.toList.map(_ == '1'))*/
        val tab = semtab(formula)
        val req = List.range(1, formula.length + 1).map(_ > 0)
        tab.zipWithIndex.filter((x: Line, y: Int) => x != req).map(_(1).toBinaryString.toList.map(_ == '1'))


    /*def isSatisfiable(formula: List[LogicalOp]): Boolean =
        val tab = semtab(formula)
        def loop(tab: Tableau, i: Int): Boolean =
            def loop2(list: List[Boolean], j: Int): Boolean =
                if(list(j) && list.length == j+1)
                    true
                else if(list(j) && list.length != j+1)
                    loop2(list, j+1)
                else
                    false
            if(!loop2(tab(i), 0) && i + 1 == tab.length)
                false
            else if(!loop2(tab(i), 0) && i + 1 != tab.length)
                loop(tab, i + 1)
            else
                true*/