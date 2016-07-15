import React from 'react';
import {connect } from 'react-redux';
import {pushPath } from 'redux-simple-router';
import {Link } from 'react-router';
import { post_async,get_async} from '../../../actions/common';
import './index.less';
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
	   <a href="/zh/login">hello!</a>
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
