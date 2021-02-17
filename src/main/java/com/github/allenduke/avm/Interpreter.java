package com.github.allenduke.avm;

import com.github.allenduke.avm.clazz.attribute.Code;
import com.github.allenduke.avm.instructions.base.BytecodeReader;
import com.github.allenduke.avm.instructions.base.Instruction;
import com.github.allenduke.avm.instructions.base.InstructionFactory;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.JThread;

/**

 */
public class Interpreter {

    public static void execute(Code code) throws Exception {
        int max_stack = code.getMax_stack();
        int max_locals = code.getMax_locals();
        byte[] byteCode = code.getCode();
        JThread jthread = new JThread(max_stack);
        Frame frame = new Frame(jthread, max_locals, max_stack);
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
            opcode = reader.read8();
            System.out.print("    opcode:" + opcode);
            Instruction instruction = InstructionFactory.getByOpcode(opcode);
            instruction.fetchOperands(reader);
            frame.setNextPc(reader.getPc());
            instruction.execute(frame);
            System.out.print("   op:" + instruction.getReName());
            System.out.println("   localVars:" + frame.getLocalVars());
        } while (opcode != 0xb1);

    }
}
