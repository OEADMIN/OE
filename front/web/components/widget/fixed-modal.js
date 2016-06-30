import React, {PropTypes } from 'react';
import * as A from 'antd';
import './fixed-modal.less'
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

        return (<div className="cs-component-widget-fixedmodal">
                    <div className="cs-cwfm-wrap" >
                        <div className={"cs-cwfm-mask "+(visible?'':'cs-cwfm-mask-hidden')} ></div>
                        <div className={"cs-cwfm "+(visible?'':'cs-cwfm-mask-hidden')} role="dialog">
                            <div className="cs-cwfm-content" ><a className="cs-cwfm-close" ><span className="cs-cwfm-close-x" ></span></a>
                                {
                                    this.props.title
                                    ?(<div className="cs-cwfm-header" >
                                        <div className="cs-cwfm-title" >
                                            {this.props.title}
                                        </div>
                                    </div>)
                                    :null
                                }
                                
                                <div className="cs-cwfm-body" >
                                    {this.props.children}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>)
    }
});

export default Process