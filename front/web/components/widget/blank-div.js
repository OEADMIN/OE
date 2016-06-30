import React, {PropTypes, Component } from 'react'
import ReactDOM from 'react-dom'
import {Router, Route, Link } from 'react-router'
import {connect } from 'react-redux'
import { pushPath } from 'redux-simple-router'
import LazyLoad from 'react-lazy-load';

import BlankImg from "../../resource/images/blank_ic_no_normal_.png";
import './blank.less';


let Audio=React.createClass({
    propsType:{
        url:React.PropTypes.string,
    },
    getInitialState:function(){
        return {
            
        }
    },

    componentWillMount:function(){

    },

    render:function(){
        let {src,text,bgSrc} = this.props;

        let text_ui = typeof text == "string"?text:(text);

        return (
                <div className="blank-div">
                    <div><img src={BlankImg}/></div>
                    <div className="text">{text_ui}</div>
                </div>
            );
    }
})

export default Audio;

