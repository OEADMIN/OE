import React, {PropTypes } from 'react';
import * as A from 'antd';
import './fixed-div.less'
//进度条组件
let Process = React.createClass({
    propTypes:{
        first:React.PropTypes.string,
        step:React.PropTypes.number,
        data:React.PropTypes.object,
        onClose:React.PropTypes.func,
    },
    getInitialState:function(){
        return {
            seconds:this.props.seconds || 5,
        }
    },
    componentWillMount: function() {
        console.log(1121)
    },

    handleOk:function() {
        console.log('点击了确定');
        this.setState({
            visible: false,
        });
    },
    render:function(){
        let { data,onClose,visible} = this.props;

        return (<div className="cs-component-widget-fixeddiv">
                    {this.props.children}
                </div>)
    }
});

export default Process