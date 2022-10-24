package project

/**
 * @file src/operators.scala
 * @brief File containing the ADT for the logical operators
 * @author Mae K.
 * @author Mustafa-Can K.
 * @author Jordan D.
 * @see (TODO pointer vers le PDF)
 */

object Operators:
    enum LogicalOp[+A]:
        case And(left: LogicalOp[A], right: LogicalOp[A])
        case Or(left: LogicalOp[A], right: LogicalOp[A])
        case Not(op: LogicalOp[A]) 
        case IfThen(left: LogicalOp[A], right: LogicalOp[A])
        case Atom(op: A) 