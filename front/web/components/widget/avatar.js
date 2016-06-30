import React, {PropTypes, Component } from 'react'
import ReactDOM from 'react-dom'
import {Router, Route, Link } from 'react-router'
import {connect } from 'react-redux'
import { pushPath } from 'redux-simple-router'
import LazyLoad from 'react-lazy-load';

import * as A from 'antd'
import './avatar.less';


let Image=React.createClass({
    propsType:{
        url:React.PropTypes.string,
    },
    getInitialState:function(){
        return {
            show_image:false,
            url:'',
        }
    },
    getDefaultProps: function() {
        return {
            src:"",//图片src
            errorsrc:"",//src图片失败时加载图片
            circle:false,//是否是圆形
            border:false,//是否有border
            size: 100,//高度
            lazy:false,//是否懒加载
            lazyheight: 100,//懒加载时的图片高度
            lazyoffset:100,//懒加载时的bottom offset
        };
    },
    componentWillMount:function(){
        //要先走检查流程
        this.check_image();
    },
    check_image:function(){
        let _this = this;
        let {src,errorsrc}=this.props;
        if(src){
            if(errorsrc){
                let img = document.createElement('img');
                
                img.onerror=function(){
                    _this.setState({
                        show_image:true,
                        url:errorsrc,
                    })
                }
                img.onload=function(){
                    _this.setState({
                        show_image:true,
                        url:src,
                    })
                }
                img.src=src;
            }else{
                _this.setState({
                    show_image:true,
                    url:src,
                })
            }
        }else{
            //nothing
        }
    },
    render:function(){
        let {size,style,lazy,lazyoffset,lazyheight,circle,border} = this.props;

        let {show_image,url}=this.state;

        if(show_image && url){
            style = Object.assign({
                backgroundImage:"url("+url+")",
                'width':size+"px",
                'height':size+"px",
                "borderRadius":size+"px"
            },style);

            let image_ui = (<div className="cs-ccwa-avatar" style={style}></div>)

            if(lazy){
                image_ui = (<LazyLoad 
                                height={lazyheight} 
                                offsetVertical={lazyoffset}>

                                {image_ui}
                            </LazyLoad>)
            }
            return (<div className="cs-component-widget-avatar">
                        {image_ui}
                    </div>)
        }else{
            return null;
        }
    }
})

export default Image;

