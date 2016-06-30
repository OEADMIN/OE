import React, {Component, PropTypes } from 'react';
import {bindActionCreators } from 'redux';
import {connect } from 'react-redux';
import {pushPath } from 'redux-simple-router';
import {Router, Route, Link } from 'react-router';

import { post_async,get_async} from '../../actions/common';


import './index.less';

import Footer from '../../components/footer';

let Spa = React.createClass({
    getInitialState:function(){
        return {
        }
    },
    componentWillMount:function(){
    },

    _ajax_member:function(){
    },

    render: function() {
        let _this = this;
	return (
	 <div>hello!</div>
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
