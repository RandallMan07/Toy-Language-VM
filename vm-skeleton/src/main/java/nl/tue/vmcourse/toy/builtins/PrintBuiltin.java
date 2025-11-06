package nl.tue.vmcourse.toy.builtins;

import nl.tue.vmcourse.toy.bci.BciTracer;
import nl.tue.vmcourse.toy.bci.OpCode;
import nl.tue.vmcourse.toy.bci.value.Value;
import nl.tue.vmcourse.toy.interpreter.ToyAbstractFunctionBody;
import nl.tue.vmcourse.toy.lang.RootCallTarget;
import nl.tue.vmcourse.toy.lang.VirtualFrame;

import java.util.List;
import java.util.Map;

public class PrintBuiltin extends ToyAbstractFunctionBody {

    @Override
    public Object execute(VirtualFrame frame) {
        Object arg = frame.get(0);

        if (!(arg instanceof Value)) throw new RuntimeException("Unexpected argument type, should be <String> but got " + arg.getClass().getSimpleName());

        ((Value) arg).print();
        return null;
    }

    @Override
    public byte[] getCode() {
        throw new RuntimeException("Built-in doesn't have a bytecode!");
    }

    @Override
    public List<Object> getPool() {
        throw new RuntimeException("Built-in doesn't have a constant pool that goes along a bytecode!");
    }

    @Override
    public void setFunctionTable(Map<String, RootCallTarget> tb) {}

    @Override
    public void setTracer(BciTracer stderr) {
        stderr.onExec(0xFFFF, OpCode.PRINTLN, null);
    }
}
