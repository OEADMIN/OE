import React, {PropTypes, Component } from 'react'
import ReactDOM from 'react-dom'
import {Router, Route, Link } from 'react-router'
import {connect } from 'react-redux'
import { pushPath } from 'redux-simple-router'
import {post_async,base_url } from '../../actions/common'

import queryString from 'query-string'

let Upload  =React.createClass({
    propsTypes:{
        data:React.PropTypes.object,
        multiple:React.PropTypes.bool,
        action:React.PropTypes.string,
        onSucess:React.PropTypes.func,
    },
    getInitialState:function(){
        return {
            ref:'input_'+(new Date()).getTime(),
        }
    },
    _on_change:function(e){
        var s =e.target.files;
        if(this.props.beforeChange){
            this.props.beforeChange();
        }
        if(s && s.length>0){
            this._ajax_upload();
        }
    },
    _ajax_upload:function(){
        let {onSucess} = this.props;
        let callback = function(json){};

        if(onSucess && onSucess instanceof Function){
            callback = onSucess;
        }
        let ref = this.state.ref;
        let input = ReactDOM.findDOMNode(this.refs[ref]);

        let form_data = new FormData()
        for(let i=0;i<input.files.length;i++){
            form_data.append('File[]', input.files[i])
        }

        //追加属性data
        if(this.props.data){
            let data = this.props.data;

            for(let k in data){
                form_data.append(k, data[k]);
            }
        }
        fetch(base_url+"/"+this.props.action, {
            method: 'POST',
            credentials: 'include',//跨域访问
            headers: {
                        // "Content-Type": "multipart/form-data"
                    },
            body: form_data
        })
        .then(response => response.json())
        .then(function(json){
            callback(json);
        })
        .catch(function(ex) {
            callback({
                s:0,
                m:ex.toString()
            })
        })
    },
    render:function(){
        let {action,multiple} = this.props;

        return (<span>
                    <input 
                        type="file" 
                        multiple={multiple?"multiple":false} 
                        ref={this.state.ref} 
                        name = {this.props.name}
                        onChange={(e)=>this._on_change(e)}/>
                </span>)
    }
})

export default Upload;