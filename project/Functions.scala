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
import project.Utils.extractVar

type Tableau = List[List[Boolean]]

object Functions:
    def semtab(formula: List[LogicalOp]): Tableau = 
        val vars = extractVar(formula).toList
        List.range(0, math.pow(2, vars.length).toInt)
            .map((x: Int) => String.format(s"%0${vars.length}d", x.toBinaryString.toInt))
            .map(_.zipWithIndex.foldLeft(Map.empty[String, Char])((acc, x) => acc + (vars(x._2) -> x._1)))
            .map((x: Map[String, Char]) => formula.map(_.eval(x)))
