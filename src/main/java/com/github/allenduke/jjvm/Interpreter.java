package com.github.allenduke.jjvm;

import com.github.allenduke.jjvm.instructions.base.BytecodeReader;
import com.github.allenduke.jjvm.instructions.base.Instruction;
import com.github.allenduke.jjvm.instructions.base.InstructionFactory;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.JThread;
import com.github.allenduke.jjvm.rtda.heap.Class;
import com.github.allenduke.jjvm.rtda.heap.ClassLoader;
import com.github.allenduke.jjvm.rtda.heap.*;

import java.util.List;

public class Interpreter {

    public static void execute(Method mainMethod, List<String> args) throws Exception {
        int maxStack = (int) mainMethod.getMaxStack();
        int maxLocals = (int) mainMethod.getMaxLocals();
        byte[] byteCode = mainMethod.getCode();
        JThread jthread = new JThread(1024);
        Frame frame = new Frame(jthread, maxLocals, maxStack, mainMethod);
        jthread.pushFrame(frame);
        AObject jArgs = createArgsArray(mainMethod.getClazz().getClassLoader(), args);
        frame.getSlots().setRef(0, jArgs);
        loop(jthread, byteCode);
    }

    public static void loop(JThread jthread, byte[] byteCode) throws Exception {
        Frame frame = null;
        BytecodeReader reader = new BytecodeReader(byteCode, 0);
        int opcode;
        do {
            frame = jthread.topFrame();
            int pc = frame.getNextPc();
            jthread.setPc(pc);
            reader.reset(frame.getMethod().getCode(), pc);
            opcode = reader.readUInt8();
            Instruction instruction = InstructionFactory.getByOpcode(opcode);
            instruction.fetchOperands(reader);
            frame.setNextPc(reader.getPc());
            instruction.execute(frame);
//            System.out.print("pc:" + pc);
//            System.out.print("    opcode:" + opcode);
//            System.out.print("   op:" + instruction.getReName());
//            System.out.print("   operandStack:" + frame.getOperandStack());
//            System.out.println("   localVars:" + frame.getSlots());
        } while (!jthread.isStackEmpty());

    }

    private static AObject createArgsArray(ClassLoader loader, List<String> args) {
        Class stringClass = loader.loadClass("java/lang/String");
        AObject argsArr = stringClass.arrayClass().newArray(args.size());
        AObject[] jArgs = argsArr.refs();
        for (int i = 0; i < args.size(); i++) {
            jArgs[i] = StringPool.JString(loader, args.get(i));
        }
        return argsArr;
    }
}
