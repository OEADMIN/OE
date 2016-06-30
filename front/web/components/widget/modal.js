import React, {PropTypes } from 'react';
import * as A from 'antd';
import './modal.less'
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
        this.setState({
            visible: false,
        });
    },
    render:function(){
        let { data,onClose,visible} = this.props;

        return (<div className="cs-componet-widget-modal">
                    <div className="cs-cwm-wrap" >
                        <div className={"cs-cwm-mask "+(visible?'':'cs-cwm-mask-hidden')} ></div>
                        <div className={"cs-cwm "+(visible?'':'cs-cwm-mask-hidden')} role="dialog">
                            <div className="cs-cwm-content" ><a className="cs-cwm-close" ><span className="cs-cwm-close-x" ></span></a>
                                {
                                    this.props.title
                                    ?(<div className="cs-cwm-header" >
                                        <div className="cs-cwm-title" >
                                            {this.props.title}
                                        </div>
                                    </div>)
                                    :null
                                }
                                
                                <div className="cs-cwm-body" >
                                    {this.props.children}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>)
    }
});

export default Process