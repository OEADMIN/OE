import React, {PropTypes, Component } from 'react'
import {Router, Route, Link } from 'react-router'
import {connect } from 'react-redux'
import { pushPath } from 'redux-simple-router'
import * as _ from 'lodash'
import * as A from 'antd'
import queryString from 'query-string'
import $ from "jquery";
import './scroll-load.less';

let ScrollLoad = React.createClass({
    getInitialState:function(){
        return {}
    },

    _bind:function(event,handler){
        if (window.addEventListener) {
            window.addEventListener(event, handler, false);
        } else if (window.attachEvent) {
            window.attachEvent('on'+event, handler);
        }
    },
    _unbind:function(event,handler){
        if (window.removeEventListener) {
            window.removeEventListener(event, handler, false);
        } else if (window.detachEvent) {
            window.detachEvent('on'+event, handler);
        }
    },
    componentWillMount:function(){
        this._bind("scroll", this.handleScroll);
    },
    componentWillUnmount:function(){
        this._unbind("scroll", this.handleScroll);
    },
    handleScroll:function(){
        let _this = this;
        let {onScrollLoad,loading,isEnd} = this.props;

        let windowHeight = $(document).height();
        let inHeight = window.innerHeight;
        let scrollT = $(window).scrollTop();
        if(scrollT + innerHeight + 300>windowHeight){  //user reached at bottom
            if(!loading &&!isEnd){  //to avoid multiple request 
                if(!_this.time){
                    _this.time = setTimeout(function(){
                        if(onScrollLoad  && onScrollLoad instanceof Function){
                            onScrollLoad();
                        }
                        _this.time = null;
                        clearTimeout(_this.time)
                    },800)
                }
            }
        }
    },
    render:function(){
        let {isEnd,loading}=this.props;
        let end_ui = null;
        let loading_ui = null;
        if(isEnd){
            end_ui=(<div className="text-center color-999 padding-16">已经没有更多了</div>)
        }
        if(loading){
            loading_ui=(<div className="text-center color-999 padding-16">加载中...</div>)
        }
        return (<div {...this.props}>
                    {this.props.children}

                    {loading_ui}
                    {end_ui}
                </div>)
    }
})

export default ScrollLoad;