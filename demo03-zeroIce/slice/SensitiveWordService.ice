#pragma once

#include "SensitiveWordModel.ice"

module com
{
    module hxl
    {
        module server
        {
            module MyMgr
            {
                ["java:type:java.util.ArrayList"]
                sequence<string> TestList;

                ["ami", "amd"] interface SensitiveWordService
                {
                    int TestToList(int companyId, out TestList list);

                    int GetSensitiveWord(int roomId, out model::SensitiveWordModel sensitiveWordList);
                };
            };
        };
    };
};