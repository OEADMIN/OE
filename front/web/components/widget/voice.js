import React, {PropTypes, Component } from 'react'
import ReactDOM from 'react-dom'
import {Router, Route, Link } from 'react-router'
import {connect } from 'react-redux'
import { pushPath } from 'redux-simple-router'

import * as A from 'antd'
import './alert.less';

import normal from "../../resource/images/listen_ic_recording_normal.png"
import recording from "../../resource/images/recording.gif"
import doing from "../../resource/images/listen_ic_suspend_normal.png"
import stop from "../../resource/images/listen_ic_play_normal.png"

let YMField=React.createClass({
    propsType:{
        url:React.PropTypes.string,
    },
    getInitialState:function(){
        return {
            result:{
                "normal":normal,
                recording:recording,
                "stop":stop,
                "doing":doing
            }
        }
    },
    render:function(){
        let 
            {
                statue = "normal",
                size = ".85rem",
                onClick
            } = this.props;
        let {result} = this.state;
        let width = ".2rem";
        if(statue=="recording"){
            width = '.35rem';
        }
        let style = {
            borderRadius:size,
            display:"inline-block",
            height:size,
            width:size,
            backgroundColor:"#FE5B70",
            backgroundImage:"url("+result[statue]+")",
            backgroundRepeat:" no-repeat",
            backgroundPosition:" center center",
            backgroundSize:width+" 38px"
        }
        return (<div className="dada-voice" style={style} onClick={()=>onClick()}>
                    
                </div>)
    }
})

export default YMField;
