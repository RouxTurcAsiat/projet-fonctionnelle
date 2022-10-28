package tests

import project.Utils.*
import project.Operators.LogicalOp,LogicalOp.*
import tests.TestFramework

object Utils extends TestFramework:
    def extractVarTest: Result[Set[String]] =
        val formula: List[LogicalOp] = List(
            Or(Atom("p"), Atom("q")),
            Atom("r"),
            Atom("q")
        )

        val output = Set("p", "q", "r")

        this.assertEqual(extractVar(formula), output)