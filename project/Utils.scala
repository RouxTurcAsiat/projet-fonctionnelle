package project

/**
 * @file src/Function.scala
 * @brief File containing all the utility functions used in the project
 * @author Mae K.
 * @author Mustafa-Can K.
 * @author Jordan D.
 * @see (TODO pointer avec le PDF)
 */
import project.Operators.LogicalOp,LogicalOp.*

object Utils:

    /**
     * @brief Function that extracts the variables from a formula
     * @param formula The formula to extract the variables from
     * @param acc, an optional argument, the accumulator for the variables
     * @return The set of variables in the formula
     */

    @annotation.tailrec 
    def extractVar(formulas: List[LogicalOp], acc: List[String] = List()): Set[String] = formulas match
            case Nil => acc.sorted.toSet
            case x :: xs => x match
                case Atom(a) => extractVar(xs, acc :+ a)
                case Not(a) => extractVar(xs :+ a , acc)
                case And(a, b) => extractVar(xs :+ a :+ b, acc)
                case Or(a, b) => extractVar(xs :+ a :+ b, acc)
                case IfThen(a, b) => extractVar(xs :+ a :+ b, acc)