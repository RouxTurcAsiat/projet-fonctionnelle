package project

/**
 * @file src/Operators.scala
 * @brief File containing the ADT for the logical operators
 * @author Mae K.
 * @author Mustafa-Can K.
 * @author Jordan D.
 * @see (TODO pointer vers le PDF)
 */

object Operators:
    enum LogicalOp:
        case And(left: LogicalOp, right: LogicalOp)
        case Or(left: LogicalOp, right: LogicalOp)
        case Not(op: LogicalOp) 
        case IfThen(left: LogicalOp, right: LogicalOp)
        case Atom(op: String) 