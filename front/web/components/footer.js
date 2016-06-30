import React, {PropTypes } from 'react'

import {Link } from 'react-router'
import {connect } from 'react-redux'
import { pushPath } from 'redux-simple-router'

let Footer = React.createClass({
    getInitialState: function() {
        return {
            alert:null,
            loading:false,
        };
    },
    render: function() {
        let { dispatch,path } = this.props;
        let style={
            "marginBottom":"0.5rem"
        }
        let splitPath = path.split('/');
        splitPath = splitPath.slice(1,4).join('/');
        let menu = [{
            title:'榜单',
            type:'edit',
            link:'/spa/rank/index',
            activePath:['spa/rank/index','spa/rank/new']
        },{
            title:'收听',
            type:'book',
            link:'/spa/listen/latest',
            activePath:['spa/listen/latest',"spa/listen/list"]
        },{
            title:'我',
            type:'user',
            link:'/spa/member/menu',
            activePath:[
                'spa/member/menu',
                'spa/member/myInfo',
                'spa/question/answer',
                'spa/question/answerAll',
                'spa/question/answerDetail',
                'spa/question/ask',
                'spa/question/listen',
                'spa/member/qrcode',
                'spa/question/askDetail'
            ]
        }];
        let menu_ui = menu.map(function(item,index){
            return (<A.Col span="8" className={"cs-link "+(item.activePath.indexOf(splitPath)>-1?'select':'')} >
                        <Link to={item.link}>

                            <p>
                                {item.title}
                            </p>
                        </Link>
                    </A.Col>)
        })
        return (
                <div>
                    <div className="line-height"></div>
                    <div className="cs-footer">
                        <A.Row className="cs-footer-content">
                            {menu_ui}
                        </A.Row>
                    </div>
                </div>
                )
    }
});

export default Footer;
