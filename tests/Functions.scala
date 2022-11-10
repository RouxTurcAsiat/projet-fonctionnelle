package tests

import project.Operators.LogicalOp, LogicalOp.*
import project.Functions.semtab, project.Tableau

object Semtab extends TestFramework:
    def testProjectStatement: Result[Tableau] =
        val formula: List[LogicalOp] = List(
          Or(Atom("p"), Atom("q")),
          Atom("r"),
          Not(Atom("q")),
        )
        val formula2: List[LogicalOp] = List(
          Nand(Atom("a"), Atom("b"))
        )

        val output = List(
            List(false, false, true),
            List(false, true, true),
            List(true, false, false),
            List(true, true, false),
            List(true, false, true),
            List(true, true, true),
            List(true, false, false),
            List(true, true, false)
        )

        val output2 = List(
          List(true),
          List(true),
          List(true),
          List(false)
        )

        //this.assertEqual(semtab(formula), output)

        this.assertEqual(semtab(formula2), output2)

