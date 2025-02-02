package com.ujwal.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `Simple expression is properly parsed`() {
        // 1.Given
        parser = ExpressionParser(calculation = "3+5-3x4/3")

        // 2. Do something with what's given
        val actual = parser.parse()

        // 3. ASSERT EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0)
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with parentheses is properly parsed`() {
        // 1.Given
        parser = ExpressionParser(calculation = "4-(4x5)")

        // 2. Do something with what's given
        val actual = parser.parse()

        // 3. ASSERT EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing),
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with decimal is properly parsed`() {
        // 1.Given
        parser = ExpressionParser(calculation = "3.2+5.8-3.6x4.1/3.5")

        // 2. Do something with what's given
        val actual = parser.parse()

        // 3. ASSERT EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(3.2),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.8),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.6),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.1),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.5)
        )
        assertThat(expected).isEqualTo(actual)
    }
}