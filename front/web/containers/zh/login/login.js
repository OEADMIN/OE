import React from 'react';
import {connect } from 'react-redux';
import {pushPath } from 'redux-simple-router';
import {Link } from 'react-router';
import { Ajax} from '../../../actions/common';
import './login.less';
let Spa = React.createClass({
    getInitialState:function(){
        return {
            formClass:"",
            formHeight:"",
            error_msg_login:"",
            error_msg_registe:"",
            email:"",
            password:"",
            cdomain :"",
            cname   :"",
            ucode   :"",
            uname   :"",
            uemail  :"",
            upass   :""
        }
    },
    componentWillMount:function(){
        this._ajax_member();
    },

    _ajax_member:function(){
        let {dispatch} = this.props;
        Ajax.post('/api/user/getuser',{name:"jayie"},function(val){
            console.log(val);
        })
    },
    do_login:function(ev){
        let {email,password} = this.state;
        ev.preventDefault();
        Ajax.get('/api/host/signin',{logincode:email,pass:password},function(val){
            console.log(val);
        })
    },
    do_registe:function(ev){
        let {cdomain,cname,ucode,uname,uemail,upass} = this.state;
        ev.preventDefault();
        let params = {
            cdomain:cdomain,
            cname:cname,
            ucode:ucode,
            uemail:uemail,
            upass:upass
        }
        Ajax.put('/api/host/signup',params,function(val){
            console.log(val);

        })
    },
    handleChg:function(name,ev){
        let obj = {};
        obj[name]=ev.target.value
        this.setState(obj);
    },
    translate:function(type){
        if(type=="registe"){
            this.setState({
                formClass:"active",
                formHeight:"520px"
            })
        }else{
            this.setState({
                formClass:"",
                formHeight:""
            })
        }
    },
    render: function() {
        let _this = this;
        let {error_msg_login,error_msg_registe,formClass,formHeight} = this.state;
    	return (
    	   <div className="page-login">
                <div className="center-content">
                    <div className={"form "+formClass} style={{height:formHeight}}>
                        <div className="form-box box-1">  
                            <form>
                                <div className="header">OE SERVICE后台管理系统登录<span className="btn-registe" onClick={()=>this.translate('registe')}>企业注册></span></div>
                                <div className="content">
                                    <p>
                                        <input type="text" value={this.state.email} onChange={(ev)=>this.handleChg("email",ev)} placeholder="管理员邮箱"/>
                                    </p>
                                    <p>
                                        <input type="password" value={this.state.password} onChange={(ev)=>this.handleChg("password",ev)} placeholder="管理员密码"/>
                                    </p>
                                    {   
                                        error_msg_login?(
                                            <p className="error">error_msg_login</p>
                                            ):null
                                    }
                                    <p>
                                        <button className="btn-login" onClick={(ev)=>this.do_login(ev)}>登录</button>
                                    </p>
                                </div>
                            </form>
                        </div>
                        <div className="form-box box-2">
                            <form>
                                <div className="header">OE SERVICE系统注册<span className="btn-registe" onClick={()=>this.translate('login')}>登录></span></div>
                                <div className="content">
                                    <p>
                                        <input type="text" value={this.state.cdomain} onChange={(ev)=>this.handleChg("cdomain",ev)} placeholder="企业域"/>
                                    </p>
                                    <p>
                                        <input type="text" value={this.state.cname} onChange={(ev)=>this.handleChg("cname",ev)} placeholder="企业名"/>
                                    </p>
                                    <p>
                                        <input type="text" value={this.state.ucode} onChange={(ev)=>this.handleChg("ucode",ev)} placeholder="用户代码"/>
                                    </p>
                                    <p>
                                        <input type="text" value={this.state.uname} onChange={(ev)=>this.handleChg("uname",ev)} placeholder="用户姓名"/>
                                    </p>
                                    <p>
                                        <input type="text" value={this.state.uemail} onChange={(ev)=>this.handleChg("uemail",ev)} placeholder="用户邮箱"/>
                                    </p>
                                    <p>
                                        <input type="password" value={this.state.upass} onChange={(ev)=>this.handleChg("upass",ev)} placeholder="用户密码"/>
                                    </p>
                                    {   
                                        error_msg_registe?(
                                            <p className="error">error_msg_registe</p>
                                            ):null
                                    }
                                    <p>
                                        <button className="btn-login" onClick={(ev)=>this.do_registe(ev)}>提交</button>
                                    </p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
           </div>
    	)
    }
});

function mapStateToProps(state) {
    return {
        member: state.member,
        path: state.routing.path
    }
}

export default connect( mapStateToProps )(Spa)
