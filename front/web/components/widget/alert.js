import React, {PropTypes, Component } from 'react'
import ReactDOM from 'react-dom'
import {Router, Route, Link } from 'react-router'
import {connect } from 'react-redux'
import { pushPath } from 'redux-simple-router'

import * as A from 'antd'
import './alert.less';


let YMField=React.createClass({
    propsType:{
        url:React.PropTypes.string,
    },
    getInitialState:function(){
        return {
        }
    },
    render:function(){
        let title = this.props.title || "/resource/images/tmp/avatar.png";

        return (<div className="cs-alert cs-avatar-primary">
                    <A.Row>
                        <A.Col span="22">
                            {title}
                        </A.Col>
                        <A.Col span="2" className="text-right
                        ">
                            <A.Icon type="right"/>
                        </A.Col>
                    </A.Row>
                </div>)
    }
})

export default YMField;
