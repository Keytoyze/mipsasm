/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.mipsasm.assembler;

import me.zhanghai.mipsasm.util.BitArray;

public class InstructionAssemblers {

    private InstructionAssemblers() {}

    private static Register getSource(Instruction instruction) {
        return (Register) instruction.getOperand(OperandPrototypes.SOURCE);
    }

    private static Register getSource2(Instruction instruction) {
        return (Register) instruction.getOperand(OperandPrototypes.SOURCE2);
    }

    private static Register getDestination(Instruction instruction) {
        return (Register) instruction.getOperand(OperandPrototypes.DESTINATION);
    }

    private static Immediate getImmediate(Instruction instruction) {
        return (Immediate) instruction.getOperand(OperandPrototypes.IMMEDIATE);
    }

    private static Offset getOffset(Instruction instruction) {
        return (Offset) instruction.getOperand(OperandPrototypes.OFFSET);
    }

    private static ShiftAmount getShiftAmount(Instruction instruction) {
        return (ShiftAmount) instruction.getOperand(OperandPrototypes.SHIFT_AMOUNT);
    }

    private static CoprocessorFunction getCoprocessorFunction(Instruction instruction) {
        return (CoprocessorFunction) instruction.getOperand(OperandPrototypes.COPROCESSOR_FUNCTION);
    }

    private static Target getTarget(Instruction instruction) {
        return (Target) instruction.getOperand(OperandPrototypes.TARGET);
    }

    private static OffsetBase getOffsetBase(Instruction instruction) {
        return (OffsetBase) instruction.getOperand(OperandPrototypes.OFFSET_BASE);
    }

    private static Register getHint(Instruction instruction) {
        return ((Register) instruction.getOperand(OperandPrototypes.HINT));
    }

    private static WordImmediate getWordImmediate(Instruction instruction) {
        return (WordImmediate) instruction.getOperand(OperandPrototypes.WORD_IMMEDIATE);
    }

    private static Label getLabel(Instruction instruction) {
        return (Label) instruction.getOperand(OperandPrototypes.LABEL);
    }

    public static final InstructionAssembler DESTINATION_SOURCE_SOURCE2 = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) {
            Operation operation = instruction.getOperation();
            context.appendAssemblyByWord(BitArray.of(
                    operation.getCode(),
                    getSource(instruction).assemble(context),
                    getSource2(instruction).assemble(context),
                    getDestination(instruction).assemble(context),
                    ShiftAmount.ZERO.assemble(context),
                    operation.getFunction()));
        }
    };

    public static final InstructionAssembler SOURCE2_SOURCE_IMMEDIATE = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) {
            context.appendAssemblyByWord(BitArray.of(
                    instruction.getOperation().getCode(),
                    getSource(instruction).assemble(context),
                    getSource2(instruction).assemble(context),
                    getImmediate(instruction).assemble(context)
            ));
        }
    };

    public static final InstructionAssembler SOURCE_SOURCE2_OFFSET = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) throws AssemblerException {
            context.appendAssemblyByWord(BitArray.of(
                    instruction.getOperation().getCode(),
                    getSource(instruction).assemble(context),
                    getSource2(instruction).assemble(context),
                    getOffset(instruction).assemble(context)
            ));
        }
    };

    public static InstructionAssembler SOURCE_OFFSET(final Register destination) {
        return new BaseInstructionAssembler() {
            @Override
            public void assemble(Instruction instruction, AssemblyContext context) throws AssemblerException {
                context.appendAssemblyByWord(BitArray.of(
                        instruction.getOperation().getCode(),
                        getSource(instruction).assemble(context),
                        destination.assemble(context),
                        getOffset(instruction).assemble(context)
                ));
            }
        };
    }

    public static final InstructionAssembler COPROCESSOR_FUNCTION = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) {
            context.appendAssemblyByWord(BitArray.of(
                    instruction.getOperation().getCode(),
                    getCoprocessorFunction(instruction).assemble(context)
            ));
        }
    };

    public static final InstructionAssembler SOURCE_SOURCE2 = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) {
            Operation operation = instruction.getOperation();
            context.appendAssemblyByWord(BitArray.of(
                    operation.getCode(),
                    getSource(instruction).assemble(context),
                    getSource2(instruction).assemble(context),
                    Register.ZERO.assemble(context),
                    ShiftAmount.ZERO.assemble(context),
                    operation.getFunction()
            ));
        }
    };

    public static final InstructionAssembler DESTINATION_SOURCE2_SHIFT_AMOUNT = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) {
            Operation operation = instruction.getOperation();
            context.appendAssemblyByWord(BitArray.of(
                    operation.getCode(),
                    Register.ZERO.assemble(context),
                    getSource2(instruction).assemble(context),
                    getDestination(instruction).assemble(context),
                    getShiftAmount(instruction).assemble(context),
                    operation.getFunction()
            ));
        }
    };

    public static final InstructionAssembler DESTINATION_SOURCE2_SOURCE = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) {
            Operation operation = instruction.getOperation();
            context.appendAssemblyByWord(BitArray.of(
                    operation.getCode(),
                    getSource(instruction).assemble(context),
                    getSource2(instruction).assemble(context),
                    getDestination(instruction).assemble(context),
                    ShiftAmount.ZERO.assemble(context),
                    operation.getFunction()));
        }
    };

    public static final InstructionAssembler TARGET = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) throws AssemblerException {
            context.appendAssemblyByWord(BitArray.of(
                    instruction.getOperation().getCode(),
                    getTarget(instruction).assemble(context)
            ));
        }
    };

    public static final InstructionAssembler DESTINATION_SOURCE = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) {
            Operation operation = instruction.getOperation();
            context.appendAssemblyByWord(BitArray.of(
                    operation.getCode(),
                    getSource(instruction).assemble(context),
                    Register.ZERO.assemble(context),
                    getDestination(instruction).assemble(context),
                    ShiftAmount.ZERO.assemble(context),
                    operation.getFunction()
            ));
        }
    };

    public static final InstructionAssembler SOURCE = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) {
            Operation operation = instruction.getOperation();
            context.appendAssemblyByWord(BitArray.of(
                    operation.getCode(),
                    getSource(instruction).assemble(context),
                    Register.ZERO.assemble(context),
                    Register.ZERO.assemble(context),
                    ShiftAmount.ZERO.assemble(context),
                    operation.getFunction()
            ));
        }
    };

    public static final InstructionAssembler SOURCE2_OFFSET_BASE = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) {
            OffsetBase offsetBase = getOffsetBase(instruction);
            context.appendAssemblyByWord(BitArray.of(
                    instruction.getOperation().getCode(),
                    offsetBase.getBase().assemble(context),
                    getSource2(instruction).assemble(context),
                    offsetBase.getOffset().assemble(context)
            ));
        }
    };

    public static final InstructionAssembler SOURCE2_IMMEDIATE = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) {
            context.appendAssemblyByWord(BitArray.of(
                    instruction.getOperation().getCode(),
                    Register.ZERO.assemble(context),
                    getSource2(instruction).assemble(context),
                    getImmediate(instruction).assemble(context)
            ));
        }
    };

    public static final InstructionAssembler DESTINATION = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) {
            Operation operation = instruction.getOperation();
            context.appendAssemblyByWord(BitArray.of(
                    operation.getCode(),
                    Register.ZERO.assemble(context),
                    Register.ZERO.assemble(context),
                    getDestination(instruction).assemble(context),
                    ShiftAmount.ZERO.assemble(context),
                    operation.getFunction()
            ));
        }
    };

    public static final InstructionAssembler HINT_OFFSET_BASE = new BaseInstructionAssembler() {
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) {
            OffsetBase offsetBase = (OffsetBase) instruction.getOperand(OperandPrototypes.OFFSET_BASE);
            context.appendAssemblyByWord(BitArray.of(
                    instruction.getOperation().getCode(),
                    offsetBase.getBase().assemble(context),
                    getHint(instruction).assemble(context),
                    offsetBase.getOffset().assemble(context)
            ));
        }
    };

    public static final InstructionAssembler LI = new InstructionAssembler() {
        @Override
        public void locate(Instruction instruction, AssemblyContext context) {
            context.advanceByWords(2);
        }
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) throws AssemblerException {
            Register source2 = getSource2(instruction);
            WordImmediate wordImmediate = getWordImmediate(instruction);
            Instruction.of(Operation.LUI, new OperandInstance[] {
                    OperandInstance.fromPrototype(OperandPrototypes.SOURCE2, source2),
                    OperandInstance.fromPrototype(OperandPrototypes.IMMEDIATE,
                            Immediate.of(wordImmediate.getUpper().value()))
            }, SOURCE2_IMMEDIATE).assemble(context);
            Instruction.of(Operation.ORI, new OperandInstance[] {
                    OperandInstance.fromPrototype(OperandPrototypes.SOURCE2, source2),
                    OperandInstance.fromPrototype(OperandPrototypes.SOURCE, source2),
                    OperandInstance.fromPrototype(OperandPrototypes.IMMEDIATE,
                            Immediate.of(wordImmediate.getLower().value()))
            }, SOURCE2_SOURCE_IMMEDIATE).assemble(context);
        }
    };

    public static final InstructionAssembler LA = new InstructionAssembler() {
        @Override
        public void locate(Instruction instruction, AssemblyContext context) {
            // instruction is unused.
            LI.locate(null, context);
        }
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) throws AssemblerException {
            Instruction.of(Operation.INVALID, new OperandInstance[] {
                    OperandInstance.fromPrototype(OperandPrototypes.SOURCE2, getSource2(instruction)),
                    OperandInstance.fromPrototype(OperandPrototypes.WORD_IMMEDIATE,
                            WordImmediate.of(context.getLabelAddress(getLabel(instruction))))
            }, LI).assemble(context);
        }
    };

    public static final InstructionAssembler MOVE = new InstructionAssembler() {
        @Override
        public void locate(Instruction instruction, AssemblyContext context) {
            context.advanceByWord();
        }
        @Override
        public void assemble(Instruction instruction, AssemblyContext context) throws AssemblerException {
            Instruction.of(Operation.ADD, new OperandInstance[] {
                    OperandInstance.fromPrototype(OperandPrototypes.DESTINATION, getDestination(instruction)),
                    OperandInstance.fromPrototype(OperandPrototypes.SOURCE, getSource(instruction)),
                    OperandInstance.fromPrototype(OperandPrototypes.SOURCE2, Register.ZERO)
            }, DESTINATION_SOURCE_SOURCE2).assemble(context);
        }
    };

    public static abstract class BaseInstructionAssembler implements InstructionAssembler {
        @Override
        public void locate(Instruction instruction, AssemblyContext context) {
            // Most instructions only offsets a word.
            context.advanceByWord();
        }
    }
}
