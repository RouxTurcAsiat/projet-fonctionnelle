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
        case Equiv(left: LogicalOp, right: LogicalOp)
        case Xor(left: LogicalOp, right: LogicalOp)
        case Nand(left: LogicalOp, right: LogicalOp)
        case Xnor(left: LogicalOp, right: LogicalOp)

        def eval(vars: Map[String, Char]): Boolean = this match
            case And(left, right) => left.eval(vars) && right.eval(vars)
            case Or(left, right) => left.eval(vars) || right.eval(vars)
            case Not(op) => !op.eval(vars)
            case IfThen(left, right) => !left.eval(vars) || right.eval(vars)
            case Atom(op) => vars(op) == '1'

            case Equiv(left, right) => IfThen(left, right) && IfThen(right, left)
            //case Equiv(left, right) => (!left.eval(vars) || right.eval(vars)) && (!right.eval(vars) || left.eval(vars))
            case Xor