import React, {PropTypes, Component } from 'react'
import ReactDOM from 'react-dom'
import {Router, Route, Link } from 'react-router'
import {connect } from 'react-redux'
import { pushPath } from 'redux-simple-router'
import LazyLoad from 'react-lazy-load';
import ListenImg from "../../resource/images/home_ic_voice_normal_min_.png";
import ListenImgGif from "../../resource/images/home_ic_voice_normal_min_.gif";
import './audio.less';
import { post_async} from '../../actions/common';
import * as A from 'antd';

let Audio=React.createClass({
    propsType:{
        url:React.PropTypes.string,
    },
    getInitialState:function(){
        let {src,action} = this.props
        return {
            show_image:false,
            url:'',
            statue:'stop',
            src:src,
            action:action
        }
    },
    getDefaultProps: function() {
        return {

        };
    },
    componentWillMount:function(){

    },
    playAudio:function(ev,id){
        ev.preventDefault();
        let {statue,action} = this.state;
        let {dispatch,text} = this.props;
        let _this = this;
        let $audio = document.getElementById("audio"+id);

        if(statue == "stop"){
            if(action == "play"){
                _this.play($audio);
            }
            if(action == 'toPay'){
                dispatch(post_async('/question/listen',{qid:id},function(obj){
                    if(obj.flag==1){
                        _this.setState({
                            src:obj.data.Answer,
                            action:"play"
                        })
                        A.message.success("付款成功，可以收听了！");
                    }else{
                        A.message.error(obj.msg)
                    }
                }))
            }

        }else{
            this.setState({statue:"stop"})
            $audio.pause();
        }

    },
    play:function($audio){
        let _this = this;
        $audio.play();
        this.setState({statue:"play"})
        let interval = setInterval(function(){
            if($audio.ended){
                clearInterval(interval);
                _this.setState({
                    statue:"stop"
                })
            }
        },10)
    },
    render:function(){
        
        // statue  = 判断播放、暂停
        // text    = 播放显示文本信息
        // isAudio = 是否显示Audio控件
        // id      = 控件ID（唯一）
        // src     = 音频文件地址
        
        let {text,id,isAudio} = this.props;
        let {statue,src} = this.state;
        let imgSrc = statue == "stop"?ListenImg:ListenImgGif;
        let ui = null;
        if(isAudio){
            ui = (
                <div>
                    <audio src={src} id={"audio"+id}></audio>
                    <div className="audio" style={{backgroundImage:"url("+imgSrc+")"}} onClick={(ev)=>this.playAudio(ev,id)}>{text}</div>
                </div>
                )
        }else{      
            ui = (<div className="audio" style={{backgroundImage:"url("+imgSrc+")"}}>{text}</div>);
        }
        return (
                <div className="audio-div">
                    {ui}
                </div>
            );
    }
})

export default Audio;