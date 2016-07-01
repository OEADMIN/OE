import { combineReducers } from 'redux'

import { TYPE } from '../constants/action-type'

export function member(state = {},action){
    switch (action.type){
        // 设置会员信息
        case TYPE.MEMBER.SET_MEMBER:
            return action.data
        default:
            return state;
    }
}
