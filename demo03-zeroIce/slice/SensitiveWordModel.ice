#pragma once

// 引入敏感词列表model
#include "SensitiveWordListModel.ice"

module com
{
    module hxl
    {
        module server
        {
            module model
            {
                ["java:getset"]
                struct SensitiveWordModel
                {
                    // 企业Id
                    long companyId;

                    // 敏感词列表
                    SensitiveWordList wordList;
                };
            };
        };
    };
};