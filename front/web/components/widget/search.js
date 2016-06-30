import React, {PropTypes, Component } from 'react'
import ReactDOM from 'react-dom'
import {Router, Route, Link } from 'react-router'
import {connect } from 'react-redux'
import { pushPath } from 'redux-simple-router'
import * as A from 'antd';
import './search.less';


let Search=React.createClass({
    propsType:{
        url:React.PropTypes.string,
    },
    getInitialState:function(){
        return {
            hidden:true
        }
    },
    componentWillMount:function(){
        
    },
    componentDidMount:function(){
        document.getElementById('searchInput').focus();
    },
    render:function(){
        let {onClose,onSearch,onChange,value} = this.props;
        onClose = onClose || function(){};
        onSearch = onSearch || function(){};
        onChange = onChange || function(){};
        return (
                <div className={"search-modal"}>
                    <A.Row className="serch-row">
                        <A.Col span="4" className="font-22 text-center"><A.Icon type="cross" onClick={onClose}/></A.Col>
                        <A.Col span="16" className="input">
                            <input type="text" 
                                id="searchInput" 
                                defaultValue={value} 
                                onChange = {(ev)=>onChange(ev.target.value)}
                                placeholder="输入昵称、头衔关键字找人"/>
                        </A.Col>
                        <A.Col span="4" className="font-22 text-center"><A.Icon type="search" onClick={onSearch}/></A.Col>
                    </A.Row>
                </div>
            );
    }
})

export default Search;

