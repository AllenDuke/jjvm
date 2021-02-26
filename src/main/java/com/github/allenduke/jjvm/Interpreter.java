package com.github.allenduke.jjvm;

import com.github.allenduke.jjvm.instructions.base.BytecodeReader;
import com.github.allenduke.jjvm.instructions.base.Instruction;
import com.github.allenduke.jjvm.instructions.base.InstructionFactory;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.JThread;
import com.github.allenduke.jjvm.rtda.heap.Method;

public class Interpreter {

    public static void execute(Method mainMethod) throws Exception {
        int maxStack = (int) mainMethod.getMaxStack();
        int maxLocals = (int) mainMethod.getMaxLocals();
        byte[] byteCode = mainMethod.getCode();
        JThread jthread = new JThread(maxStack);
        Frame frame = new Frame(jthread, maxLocals, maxStack, mainMethod);
//        frame.getSlots().setRef(0,);
        jthread.pushFrame(frame);
        loop(jthread, byteCode);
    }

    public static void loop(JThread jthread, byte[] byteCode) throws Exception {
        Frame frame = jthread.popFrame();
        BytecodeReader reader = new BytecodeReader(byteCode, frame.getNextPc());
        int opcode;
        do {
            int pc = frame.getNextPc();
            jthread.setPc(pc);
            System.out.print("pc:" + reader.getPc());
            reader.reset(byteCode, pc);
            opcode = reader.readUInt8();
            System.out.print("    opcode:" + opcode);
            Instruction instruction = InstructionFactory.getByOpcode(opcode);
            instruction.fetchOperands(reader);
            frame.setNextPc(reader.getPc());
            instruction.execute(frame);
            System.out.print("   op:" + instruction.getReName());
            System.out.print("   operandStack:" + frame.getOperandStack());
            System.out.println("   localVars:" + frame.getSlots());
        } while (opcode != Instruction.CODE_return);

    }
}
