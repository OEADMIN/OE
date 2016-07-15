import React from 'react';
import {connect } from 'react-redux';
import {pushPath } from 'redux-simple-router';
import {Link } from 'react-router';
import { post_async,get_async} from '../../../actions/common';
import './login.less';
let Spa = React.createClass({
    getInitialState:function(){
        return {
            error_msg:"",
            email:"",
            password:""
        }
    },
    componentWillMount:function(){
        this._ajax_member();
    },

    _ajax_member:function(){
        let {dispatch} = this.props;
        post_async('/api/user/getuser',{name:"jayie"},function(val){
            console.log(val);
        })
    },
    do_login:function(){
        let {email,password} = this.state;
        get_async('/api/host/signin',{logincode:email,pass:password},function(val){
            console.log(val);

        })
    },
    handleChg:function(name,ev){
        let obj = {};
        obj[name]=ev.target.value
        this.setState(obj);
    },
    render: function() {
        let _this = this;
        let {error_msg} = this.state;
    	return (
    	   <div className="page-login">
                <div className="form">
                    <div className="header">OE SERVICE后台管理系统登录<span className="btn-registe">企业注册></span></div>
                    <div className="content">
                        <p>
                            <input type="text" value={this.state.email} onChange={(ev)=>this.handleChg("email",ev)} placeholder="管理员邮箱"/>
                        </p>
                        <p>
                            <input type="password" value={this.state.password} onChange={(ev)=>this.handleChg("password",ev)} placeholder="管理员密码"/>
                        </p>
                        {   
                            error_msg?(
                                <p className="error">error_msg</p>
                                ):null
                        }
                        <p>
                            <button className="btn-login" onClick={this.do_login}>登录</button>
                        </p>
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
