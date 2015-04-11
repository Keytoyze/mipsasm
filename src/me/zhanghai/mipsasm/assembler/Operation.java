/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.mipsasm.assembler;

import me.zhanghai.mipsasm.util.BitArray;

public enum Operation {

    INVALID(),
    ADD(0b000000, 0b100000),
    ADDI(0b001000),
    ADDIU(0b001001),
    ADDU(0b000000, 0b100001),
    AND(0b000000, 0b100100),
    ANDI(0b001100),
    // B, BAL, BC1F, BC1FL, ... , BC2TL
    BEQ(0b000100),
    BEQL(0b010100),
    BGEZ(0b000001),
    BGEZAL(0b000001),
    BGEZALL(0b000001),
    BGEZL(0b000001),
    BGTZ(0b000111),
    BGTZL(0b010111),
    BLEZ(0b000110),
    BLEZL(0b010110),
    BLTZ(0b000001),
    BLTZAL(0b000001),
    BLTZALL(0b000001),
    BLTZL(0b000001),
    BNE(0b000101),
    BNEL(0b010101),
    // BREAK, C, ... , CLZ
    COP0(0b010000),
    COP1(0b010001),
    COP2(0b010010),
    COP3(0b010011),
    // CTC, ... , DERET
    DIV(0b000000, 0b011010),
    DIVU(0b000000, 0b011011),
    J(0b000010),
    JAL(0b000011),
    JALR(0b000000, 0b001001),
    JR(0b000000, 0b001000),
    LB(0b100000),
    LBU(0b100100),
    LDC1(0b110101),
    LDC2(0b110110),
    LH(0b100001),
    LHU(0b100101),
    LL(0b110000),
    LUI(0b001111),
    LW(0b100011),
    LWC1(0b110001),
    LWC2(0b110010),
//    LWC3(0b110011),
    LWL(0b100010),
    LWR(0b100110),
    // MADD, ... MFC2
    MFHI(0b000000, 0b010000),
    MFLO(0b000000, 0b010010),
    // MOV
    MOVN(0b000000, 0b001011),
    // MOVT
    MOVZ(0b000000, 0b001010),
    // MSUB, ... ,MTC2
    MTHI(0b000000, 0b010001),
    MTLO(0b000000, 0b010011),
    // MUL
    MULT(0b000000, 0b011000),
    MULTU(0b000000, 0b011001),
    NOR(0b000000, 0b100111),
    //TODO: NOOP(0b000000, 0b000000),
    OR(0b000000, 0b100101),
    ORI(0b001101),
    PREF(0b110011),
    SB(0b101000),
    SC(0b111000),
    // SDBBP
    SDC1(0b111101),
    SDC2(0b111110),
//    SDL(0b101100),
//    SDR(0b101101),
    SH(0b101001),
    SLL(0b000000, 0b000000),
    SLLV(0b000000, 0b000100),
    SLT(0b000000, 0b101010),
    SLTI(0b001010),
    SLTIU(0b001011),
    SLTU(0b000000, 0b101011),
    SRA(0b000000, 0b000011),
    SRAV(0b000000, 0b000111),
    SRL(0b000000, 0b000010),
    SRLV(0b000000, 0b000110),
    // SSNOP
    SUB(0b000000, 0b100010),
    SUBU(0b000000, 0b100011),
    SW(0b101011),
    SWC1(0b111001),
    SWC2(0b111010),
    SWC3(0b111011),
    SWL(0b101010),
    SWR(0b101110),
    // TEQ, ... , TNEI
    // TODO: SYSCALL(0b000000, 0b001100),
    XOR(0b000000, 0b100110),
    XORI(0b001110);

    private static final int CODE_LENGTH = 6;
    private static final int FUNCTION_LENGTH = 6;

    // This constant needs to be accessed in constructor, so it must be in a nested construction to get initialized
    // before it is used.
    private interface Constants {
        BitArray INVALID_CODE = BitArray.of(0b111111, CODE_LENGTH);
        BitArray INVALID_FUNCTION = BitArray.of(0b111111, FUNCTION_LENGTH);
    }

    private BitArray code;
    private BitArray function;

    Operation(BitArray code, BitArray function) {
        this.code = code;
        this.function = function;
    }

    Operation(int code, int function) {
        this(BitArray.of(code, CODE_LENGTH), BitArray.of(function, FUNCTION_LENGTH));
    }

    Operation(int code) {
        this(BitArray.of(code, CODE_LENGTH), Constants.INVALID_FUNCTION);
    }

    Operation() {
        this(Constants.INVALID_CODE, Constants.INVALID_FUNCTION);
    }

    public BitArray getCode() {
        if (code == Constants.INVALID_CODE) {
            throw new IllegalStateException("getCode() called on Operation without a valid code");
        }
        return code;
    }

    public BitArray getFunction() {
        if (function == Constants.INVALID_FUNCTION) {
            throw new IllegalStateException("getFunction() called on Operation without a valid function");
        }
        return function;
    }
}
