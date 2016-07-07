import React from 'react';
import {connect } from 'react-redux';
import {pushPath } from 'redux-simple-router';

let Spa = React.createClass({
    getInitialState:function(){
        return {
            result:null,
        }
    },
    componentWillMount:function(){
    },
    componentDidMount:function () {

    },
    render: function() {
        let _this = this;
        let {dispatch, content, path } = this.props;
        let {result}=this.state;
        return (
                    <div className="p-login">
                        {content}
                    </div>
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
