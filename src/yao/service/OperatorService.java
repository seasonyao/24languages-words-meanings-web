package yao.service;

import yao.bean.Operator;
import yao.dao.OperatorDAO;

import java.util.List;

public class OperatorService
{
    private String msg;

    public Operator login(Operator operator){
        return new OperatorDAO().findOperatorByIDAndPWD(operator);
    }
    public boolean addOperator(Operator operator){
    if(new OperatorDAO().findOperatorByID(operator.getId()) != null){
        msg = "用户名已存在";
        return false;
    } else {
        return new OperatorDAO().addOperator(operator);
    }
}

    public boolean edit(Operator operator){
        return new OperatorDAO().updateOperator(operator);
    }

    public Operator getOperator(String operatorID){
        return new OperatorDAO().findOperatorByID(operatorID);
    }

    public boolean delete(String operatorID){
        return new OperatorDAO().deleteOperator(operatorID);
    }

    public List<Operator> getAllOperators(){
        return new OperatorDAO().findAllOperators();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
