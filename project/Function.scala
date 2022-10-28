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

type Tableau = List[List[Int]]

object Function:
    def semtab(formula: List[LogicalOp]): Option[Tableau] = ???
        // val variable = extractVar(formula)